package com.example.demo.enums;

public enum GameMode {
	ONE_PLAYER(1),
	TWO_PLAYER(2);

	int value;
	GameMode(int i) {
		this.value = i;
	}
	
	public static GameMode getGameModeByValue(int i) {
		for (GameMode m : GameMode.values()) {
			if (m.getValue() == i)
				return m;
		}
		
		return null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
