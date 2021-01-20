package com.paathshala.flight.flightservice.model;

import java.util.List;

public class FlightsWithHostMetaData extends HostMetaData {

	private List<Flight> flights;

	public List<Flight> getFlights() {
		return flights;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private List<Flight> flights;
		private String podName;
		private String podIp;
		private String nameSpace;

		private Builder() {
		}

		public Builder withFlights(List<Flight> flights) {
			this.flights = flights;
			return this;
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

		public FlightsWithHostMetaData build() {
			FlightsWithHostMetaData flightsWithHostMetaData = new FlightsWithHostMetaData();
			flightsWithHostMetaData.flights = this.flights;
			flightsWithHostMetaData.nameSpace = this.nameSpace;
			flightsWithHostMetaData.podIp = this.podIp;
			flightsWithHostMetaData.podName = this.podName;
			return flightsWithHostMetaData;
		}
	}
}
