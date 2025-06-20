import Address from "./Address";
import Demand from "./Demand";

export default class DemandByAddress {
    constructor(address=new Address(), demand=new Demand()) {
        this.uf = address.state;
        this.city = address.city;
        this.district = address.district;
        this.street = address.street;
        this.title = demand.title;
        this.description = demand.description;
        this.typeDemand = demand.type;
        this.location = demand.location;
        this.observation = demand.observation;
    }
}