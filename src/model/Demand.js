import Address from "./Address";
import Location from "./Location";

export default class Demand {
    constructor(title=null, type=null, description=null, observation=null) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.observation = observation;
        this.uf = null;
        this.city = null;
        this.district = null;
        this.street = null;
        this.location = null;
        this.address = null;
    }

    setAddress(address=new Address()) {
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

