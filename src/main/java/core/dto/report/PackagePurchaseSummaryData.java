package core.dto.report;

import core.dto.PackageData;

public class PackagePurchaseSummaryData {

	private PackageData pkg;
	private Long count;

	public PackageData getPkg() {
		return pkg;
	}

	public void setPkg(PackageData pkg) {
		this.pkg = pkg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
