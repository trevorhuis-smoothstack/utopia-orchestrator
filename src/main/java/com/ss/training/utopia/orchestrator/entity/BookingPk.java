package com.ss.training.utopia.orchestrator.entity;

import java.io.Serializable;

/**
 * @author Trevor Huis in 't Veld
 */
public class BookingPk implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6923940602285234654L;
    
    private Long travelerId, flightId;

	public BookingPk() {
	}

	public BookingPk(Long travelerId, Long flightId) {
		this.travelerId = travelerId;
		this.flightId = flightId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flightId == null) ? 0 : flightId.hashCode());
		result = prime * result + ((travelerId == null) ? 0 : travelerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingPk other = (BookingPk) obj;
		if (flightId == null) {
			if (other.flightId != null)
				return false;
		} else if (!flightId.equals(other.flightId))
			return false;
		if (travelerId == null) {
			if (other.travelerId != null)
				return false;
		} else if (!travelerId.equals(other.travelerId))
			return false;
		return true;
	}

}