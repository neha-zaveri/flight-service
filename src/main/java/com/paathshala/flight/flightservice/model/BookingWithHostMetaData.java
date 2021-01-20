package com.paathshala.flight.flightservice.model;

public class BookingWithHostMetaData extends HostMetaData {

    private Flight flight;
    private String message;

    public Flight getFlight() {
        return flight;
    }

	public String getMessage() {
		return message;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		String podName;
		String podIp;
		String nameSpace;
		private Flight flight;
		private String message;

		private Builder() {
		}

		public Builder withPodName(String podName) {
			this.podName = podName;
			return this;
		}

		public Builder withPodIp(String podIp) {
			this.podIp = podIp;
			return this;
		}

		public Builder withNameSpace(String nameSpace) {
			this.nameSpace = nameSpace;
			return this;
		}

		public Builder withFlight(Flight flight) {
			this.flight = flight;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public BookingWithHostMetaData build() {
			BookingWithHostMetaData bookingWithHostMetaData = new BookingWithHostMetaData();
			bookingWithHostMetaData.message = this.message;
			bookingWithHostMetaData.podName = this.podName;
			bookingWithHostMetaData.podIp = this.podIp;
			bookingWithHostMetaData.flight = this.flight;
			bookingWithHostMetaData.nameSpace = this.nameSpace;
			return bookingWithHostMetaData;
		}
	}
}
