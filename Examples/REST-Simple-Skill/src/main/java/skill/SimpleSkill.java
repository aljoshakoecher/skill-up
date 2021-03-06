package skill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotations.Completing;
import annotations.Execute;
import annotations.SkillParameter;
import annotations.SkillOutput;
import annotations.Skill;
import annotations.Starting;

@Skill(type = "RestSkill", skillIri = "https://siemens.de/skills#RestSkill", capabilityIri = "https://siemens.de/capabilites#bestCapability", moduleIri = "https://siemens.de/modules#ModuleA")
public class SimpleSkill {

	private final Logger logger = LoggerFactory.getLogger(SimpleSkill.class);

	@SkillParameter(isRequired = true, option = {"4", "3", "2"})
	private int j;

	@SkillOutput(isRequired = false)
	private int y;

	@Starting
	public void starting() {
		logger.info("Starting, j = " + j);
	}

	@Execute
	public void execute() {
		y = j * 2;
	}

	@Completing
	public void completing() {
		logger.info("Completing, y = " + y);
	}
}
