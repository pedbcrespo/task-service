import Address from "./Address";
import Location from "./Location";

export default class Demand {
    title: string;
    type: string;
    description: string;
    observation: string;
    uf: string;
    city: string;
    district: string;
    street: string;
    location: Location;
    creationDate: Date;

    constructor(address: Address) {
        this.title = null;
        this.type = null;
        this.description = null;
        this.observation = null;

        if(!address) return;
        
        this.uf = address.state;
        this.city = address.city;
        this.district = address.district;
        this.street = address.street;
        this.location = address.location;

        this.creationDate = new Date();
    }

    getAddress() {
        return new Address(this.uf, this.city, this.district, this.street, this.location);
    }
}

