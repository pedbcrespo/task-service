import Address from "./Address";
import Location from "./Location";

export default class Demand {
    constructor(address=new Address()) {
        this.title = null;
        this.type = null;
        this.description = null;
        this.observation = null;
        this.uf = address.state;
        this.city = address.city;
        this.district = address.district;
        this.street = address.street;
        this.location = address.location;
    }

    getAddress() {
        return new Address(this.uf, this.city, this.district, this.street, this.location);
    }
}

