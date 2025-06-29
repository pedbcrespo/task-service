import Location from "./Location";

export default class Address {
    state: string;
    city: string;
    district: string;
    street: string;
    location: Location;
    
    constructor(state: string, city: string, district: string, street: string, location: Location) {
        this.state = state;
        this.city = city;
        this.district = district;
        this.street = street;
        this.location = location;
    }
}