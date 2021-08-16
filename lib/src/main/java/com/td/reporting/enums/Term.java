package com.td.reporting.enums;

/**
 * Term enum used to simplify range computation and to avoid if branching.
 *
 */
public enum Term {
	
	A {
		@Override
		public boolean isInRange(long value) {
			return 0 <= value && 1 >= value;
		}

		@Override
		public String getLabel() {
			return "0m-1m";
		}
	}, B {
		@Override
		public boolean isInRange(long value) {
			return 1 < value && 6 >= value;
		}

		@Override
		public String getLabel() {
			return "1m-6m";
		}
	}, C {
		@Override
		public boolean isInRange(long value) {
			return 6 < value && 12 >= value;
		}

		@Override
		public String getLabel() {
			return "6m-1yr";
		}
	}, D {
		@Override
		public boolean isInRange(long value) {
			return 12 < value && 120 >= value;
		}

		@Override
		public String getLabel() {
			return "1yr-10yr";
		}
	}, E {
		@Override
		public boolean isInRange(long value) {
			return 120 < value && 360 >= value;
		}

		@Override
		public String getLabel() {
			return "10yr-30yr";
		}
	}, F {
		@Override
		public boolean isInRange(long value) {
			return 360 < value && value >= 600;
		}

		@Override
		public String getLabel() {
			return "30yr-50yr";
		}
	}, G {
		@Override
		public boolean isInRange(long value) {
			return 600 < value;
		}

		@Override
		public String getLabel() {
			return "50yr+";
		}
	};
	
	public abstract boolean isInRange(long value);
	public abstract String getLabel();

}
