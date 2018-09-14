package core.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.Utility;
import core.model.IRecord;
import core.model.member.Member;
import core.model.pkg.PackageAvailment;
import core.model.pkg.PackageAvailmentSession;
import core.model.program.ProgramAvailment;
import core.model.timeentry.TimeEntry;
import core.model.timeentry.TimeEntryRepository;
import core.repository.AbstractRepository;

@Service
@Transactional
public class TimeEntryService extends AbstractService {
	
	@Autowired private TimeEntryRepository repository;
	
	@Autowired private MemberService memberService;
	@Autowired private ProgramAvailmentService programAvailmentService;
	@Autowired private PackageAvailmentService packageAvailmentService;

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractRepository getRepository() {
		return repository;
	}
	
	public List<TimeEntry> findFilteredItems(Long memberId, Long coachId,
			Integer pageSize, Integer pageOffset, String orderBy) {
		return repository.findFilteredItems(memberId, coachId,
				pageSize, pageOffset, orderBy);
	}
	
	public IRecord saveUsingAccessCard(TimeEntry timeEntry) {
		Member member = memberService.findByAccessCardNo(timeEntry.getAccessCardNoUsed());
		if (member == null) {
			throw new IllegalArgumentException("Member not found.");
		}
		timeEntry.setMember(member);
		timeEntry.setCoachAssigned(member.getDefaultCoach());
		
		Date today = Utility.getCurrentDateWithoutTime();
		
		if (repository.findTimeEntryForDate(member.getId(), today) == null) {
			timeEntry.setDate(today);

			Availments availments = getAvailmentsToUse(member);
			ProgramAvailment programAvailment = availments.programAvailment;
			PackageAvailment packageAvailment = availments.packageAvailment;

			if (programAvailment == null && packageAvailment == null) {
				throw new IllegalArgumentException("No active program/package availed for member.");
			}
			
			timeEntry.setProgramAvailment(programAvailment);
			timeEntry.setPackageAvailment(packageAvailment);
			
			return save(timeEntry);
		}
		return timeEntry;
	}
	
	@Override
	public IRecord save(IRecord record) {
		TimeEntry timeEntry = (TimeEntry) record;
		
		if (timeEntry.getProgramAvailment() != null) {
			timeEntry.setCommission(timeEntry.getProgramAvailment().getAvailedProgram().getCommission());
		} else if (timeEntry.getPackageAvailment() != null) {
			PackageAvailment packageAvailment = timeEntry.getPackageAvailment();
			timeEntry.setCommission(packageAvailment.getAvailedPackage().getCommission());
			
			PackageAvailmentSession session = new PackageAvailmentSession();
			session.setDate(timeEntry.getDate());
			session.setPackageAvailment(timeEntry.getPackageAvailment());
			session = (PackageAvailmentSession) savePlainObject(session);

			packageAvailment.setSessionsRemaining(packageAvailment.getSessionsRemaining() - 1);
			super.update(packageAvailment);
			
			timeEntry.setSession(session);
		} else {
			throw new IllegalStateException("No program/package is availed by member valid by that date.");
		}
		return super.save(record);
	}
	
	
	private Availments getAvailmentsToUse(Member member) {
		Availments availments = new Availments();
		Date today = Utility.getCurrentDateWithoutTime();

		List<ProgramAvailment> availableProgramAvailments = programAvailmentService
				.findAvailableProgramAvailments(member.getId(), today);
		List<PackageAvailment> availablePackageAvailments = packageAvailmentService
				.findAvailablePackageAvailments(member.getId(), today);
		
		ProgramAvailment programAvCandidate = !availableProgramAvailments.isEmpty() ?
				availableProgramAvailments.get(0) : null;
		PackageAvailment packageAvCandidate = !availablePackageAvailments.isEmpty() ?
				availablePackageAvailments.get(0) : null;

		if (programAvCandidate == null) {
			availments.packageAvailment = packageAvCandidate;
		} else if (packageAvCandidate == null) {
			availments.programAvailment = programAvCandidate;
		} else {
			boolean packageBeforeProgram = packageAvCandidate.getStartDate()
					.before(programAvCandidate.getStartDate());
			availments.programAvailment = !packageBeforeProgram ? programAvCandidate : null;
			availments.packageAvailment = packageBeforeProgram ? packageAvCandidate : null;
		}

		return availments;
	}
	
	@Override
	public void delete(IRecord record) {
		TimeEntry timeEntry = (TimeEntry) record;
		
		if (timeEntry.getPackageAvailment() != null && timeEntry.getSession() != null) {
			PackageAvailment packageAvailment = (PackageAvailment) packageAvailmentService
					.findByIdWithDetails(timeEntry.getPackageAvailment().getId());
			
			packageAvailment.getSessions().remove(timeEntry.getSession());
			int sessionsRemaining = packageAvailment.getSessionsCount() - packageAvailment.getSessions().size();
			packageAvailment.setSessionsRemaining(sessionsRemaining);
			super.update(packageAvailment);
		}
			
		super.delete(record);
	}
	
	private class Availments {

		ProgramAvailment programAvailment;
		PackageAvailment packageAvailment;

	}

}
