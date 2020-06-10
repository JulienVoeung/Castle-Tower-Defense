package application.modele.Case;

import application.config;

public class Mine extends Case {
	
	private int cooldownCredit;
	
	public Mine() {
		super("Mine", 51 , false);
		this.cooldownCredit = config.cooldownCreditMine;
	}

	public int getCooldownCredit() {
		return this.cooldownCredit;
	}

	public void setCooldownCredit(int cooldownCredit) {
		this.cooldownCredit = cooldownCredit;
	}

	public void decrementerCooldown() {
		this.cooldownCredit--;
	}
}
