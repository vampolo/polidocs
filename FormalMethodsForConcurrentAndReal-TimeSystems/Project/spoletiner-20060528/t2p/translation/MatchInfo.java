package t2p.translation;

public class MatchInfo {
	private String variable;
	private int value;
	private int myValue;
	private boolean strong;
	
	public int getMyValue() {
		return myValue;
	}

	public MatchInfo(int myValue, String variable, int value, boolean strong) {
		
		this.myValue = myValue;
		this.variable = variable;
		this.value = value;
		this.strong=strong;
	}

	public int getValue() {
		return value;
	}

	public String getVariable() {
		return variable;
	}

	public boolean isStrong() {
		return strong;
	}
	
	
	
}
