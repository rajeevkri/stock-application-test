package com.stockapplication.pojo;

public class OrderMatcherResponse {
	private Integer sellOrderId;
	
	private Integer quantity;
	
	private Double sellPrice;
	
	private Integer buyOrderId;

	public Integer getSellOrderId() {
		return sellOrderId;
	}

	public void setSellOrderId(Integer sellOrderId) {
		this.sellOrderId = sellOrderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Integer getBuyOrderId() {
		return buyOrderId;
	}

	public void setBuyOrderId(Integer buyOrderId) {
		this.buyOrderId = buyOrderId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((buyOrderId == null) ? 0 : buyOrderId.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result
				+ ((sellOrderId == null) ? 0 : sellOrderId.hashCode());
		result = prime * result
				+ ((sellPrice == null) ? 0 : sellPrice.hashCode());
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
		OrderMatcherResponse other = (OrderMatcherResponse) obj;
		if (buyOrderId == null) {
			if (other.buyOrderId != null)
				return false;
		} else if (!buyOrderId.equals(other.buyOrderId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (sellOrderId == null) {
			if (other.sellOrderId != null)
				return false;
		} else if (!sellOrderId.equals(other.sellOrderId))
			return false;
		if (sellPrice == null) {
			if (other.sellPrice != null)
				return false;
		} else if (!sellPrice.equals(other.sellPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "#" + sellOrderId
				+ " " + quantity + " " + sellPrice
				+ " #" + buyOrderId;
	}

}
