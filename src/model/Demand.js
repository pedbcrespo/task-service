import Location from "./Location";

export default class Demand {
    constructor(title=null, type=null, description=null, observation=null, location=new Location) {
        this.title = title;
        this.type = type;
        this.location = location;
        this.description = description;
        this.observation = observation;
    }
}

