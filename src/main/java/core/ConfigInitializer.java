package core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.model.Config;
import core.service.ConfigService;

@Component
public class ConfigInitializer {
	
	@Autowired private ConfigService configService;

	@PostConstruct
	public void setupConfigs() {
		setupMembershipAmountConfig();
	}
	
	private void setupMembershipAmountConfig() {
		Config config = configService.findByName(Constants.CONFIG_NAME_MEMBERSHIP_AMOUNT);
		if (config == null) {
			config = new Config();
			config.setName(Constants.CONFIG_NAME_MEMBERSHIP_AMOUNT);
			config.setValue(Constants.CONFIG_VALUE_MEMBERSHIP_AMOUNT);
			configService.save(config);
		}
	}

}
