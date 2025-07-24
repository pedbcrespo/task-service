import axios from "axios";

export default class CityDataCenterService {
    constructor() {
        this.url = 'http://127.0.0.1:5000/city-db-api/demand/';
    }

    async getDemands() {
        return await axios.get(this.url);
    }

    async saveDemand(demand) {
        if(!demand) return;
        await axios.post(this.url, demand);
    }
}