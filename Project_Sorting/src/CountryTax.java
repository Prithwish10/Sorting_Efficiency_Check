public class CountryTax {

	private double tax;
	private String country;
	
	public CountryTax(double tax, String country) {
		this.setTax(tax);
		this.setCountry(country);
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
