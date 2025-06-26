import axios from "axios";

export default class AddressService {
    constructor() {
        this.reverseGeoUrl = 'https://nominatim.openstreetmap.org/reverse'
    }

    async getGeoLocalization(lat, lng) {
        const params = {
          format: 'json',
          lat: lat,
          lon: lng,
          addressdetails: 1
        };
        const response = await axios.get(this.reverseGeoUrl, {
          params,
          headers: {
            'Accept-Language': 'pt-BR',
          }
        });
        return response.data.address;
    }

    async getAddressByCep(cep) {
      let url = `https://viacep.com.br/ws/${cep}/json/`;
      let res =  await axios.get(url);
      return res.data;
    }

    async getAddress(lat, lng) {
        let address = await this.getGeoLocalization(lat, lng);
        return await this.getAddressByCep(address.postcode);
    }
}