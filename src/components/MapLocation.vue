<template>
    <div class="map-location">
        <l-map
          style="height: 400px;"
          :zoom="zoom"
          :center="[lat, lng]"
          @click="onMapClick"
        >
          <l-tile-layer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <l-marker v-if="location.lat !== null" :lat-lng="[location.lat, location.lng]" />
          <l-circle-marker :lat-lng="[lat, lng]" :radius="2" color="red" />
        </l-map>
        
        <p v-if="location.lat !== null">
          <BButton variant="primary" @click="openModal">Adicionar Demanda</BButton>
        </p>

        <BModal v-model="isModalOpen" title="Adicionar Demanda">  
          <BForm>
            <BFormGroup>
              <BFormInput
                id="input-1"
                v-model="demand.title"
                type="email"
                placeholder="Enter email"
                required
              />
              <BFormSelect
                id="input-3"
                v-model="demand.type"
                :options="typeDemands"
                required
              />
            </BFormGroup>
          </BForm>
        </BModal>
    </div>
</template>

<script>
import { LMap, LTileLayer, LMarker, LCircle, LCircleMarker } from '@vue-leaflet/vue-leaflet'
import 'leaflet/dist/leaflet.css'
import L from 'leaflet'
import Location from '@/model/Location'
import Demand from '@/model/Demand'
import axios from 'axios'
import Address from '@/model/Address'

delete L.Icon.Default.prototype._getIconUrl
L.Icon.Default.mergeOptions({
  iconRetinaUrl: 'https://unpkg.com/leaflet@1.9.3/dist/images/marker-icon-2x.png',
  iconUrl: 'https://unpkg.com/leaflet@1.9.3/dist/images/marker-icon.png',
  shadowUrl: 'https://unpkg.com/leaflet@1.9.3/dist/images/marker-shadow.png'
})
export default {
  name: 'MapaComponent',
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LCircle,
    LCircleMarker
  },
  data() {
    return {
      lat: -22.9,
      lng: -43.2,
      zoom: 17,
      location: new Location(null, null),
      position: null,
      isModalOpen: false,
      demand: new Demand(),
      address: null,
      typeDemands: [],
    }
  },
  created() {
    if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      position => {
        this.position = position
      },
      error => {
        console.error('Erro ao obter localização:', error)
      }
    )
  } else {
    console.warn('Geolocalização não suportada pelo navegador.')
  }
  },
  methods: {
    onMapClick(e) {
      this.location = new Location(e.latlng.lat, e.latlng.lng)
      this.getAddress(this.location.lat, this.location.lng).then(res => {
        this.address = new Address(res.uf, res.localidade, res.bairro, res.logradouro)
        console.log(this.address);
      })
    },
    openModal() {
      this.isModalOpen = !this.isModalOpen;
    },
    async getAddress(lat, lng) {
      let address = await this.reverseGeocode(lat, lng);
      return await this.getAddressByCep(address.postcode);
    },
    async reverseGeocode(lat, lon) {
      try {
        const url = 'https://nominatim.openstreetmap.org/reverse'
        const params = {
          format: 'json',
          lat,
          lon,
          addressdetails: 1
        }
      
        const response = await axios.get(url, {
          params,
          headers: {
            'Accept-Language': 'pt-BR',
            'User-Agent': 'task-service'
          }
        })
        return response.data.address
      
      } catch (error) {
        console.error('Erro ao buscar endereço:', error)
        return null
      }
    },
    async getAddressByCep(cep) {
      let url = `https://viacep.com.br/ws/${cep}/json/`;
      let res =  await axios.get(url);
      return res.data;
    }
  },
  watch: {
    position(newPosition) {
      this.lat = newPosition.coords.latitude
      this.lng = newPosition.coords.longitude
      console.log(this.lat, this.lng);
    }
  }
}
</script>
<style>
.map-location {
    width: 100%;
    height: 100%;
}   
</style>