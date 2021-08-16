package com.td.reporting.enums;

/**
 * BreakStatus enum used to simplify range computation and to avoid if branching.
 *
 */
public enum BreakStatus {
	
	A {
		@Override
		public boolean isInRange(long value) {
			return 0 <= value && 99 >= value;
		}

		@Override
		public String getLabel() {
			return "0-99";
		}
	}, B {
		@Override
		public boolean isInRange(long value) {
			return 100 <= value && 999 >= value;
		}

		@Override
		public String getLabel() {
			return "100-999";
		}
	}, C {
		@Override
		public boolean isInRange(long value) {
			return 1000 <= value && 9999 >= value;
		}

		@Override
		public String getLabel() {
			return "1000-9999";
		}
	}, D {
		@Override
		public boolean isInRange(long value) {
			return 10000 <= value && 99999 > value;
		}

		@Override
		public String getLabel() {
			return "10000-99999";
		}
	}, E {
		@Override
		public boolean isInRange(long value) {
			return 100000 < value;
		}

		@Override
		public String getLabel() {
			return "100000+";
		}
	};
	
	public abstract boolean isInRange(long value);
	public abstract String getLabel();

}
