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
          {{ address.state }} - {{ address.city }} - {{ address.district }} {{ address.street }}
          <BButton variant="primary" @click="openModal">Adicionar Demanda</BButton>
        </p>

        <BModal v-model="isModalOpen" title="Adicionar Demanda">  
          <BForm>
            <BFormGroup>
              <BFormInput
                id="input-1"
                v-model="demand.title"
                placeholder="Nome da demanda"
                required
              />
              <BFormSelect
                id="input-3"
                v-model="demand.type"
                placeholder="Selecione a classeficação da demanda"
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
import Address from '@/model/Address'
import AddressService from '@/service/AddressService'

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
      address: new Address(),
      typeDemands: [],
      addressService: new AddressService(),
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
      this.addressService.getAddress(this.location.lat, this.location.lng).then(res => {
        this.address = new Address(res.uf, res.localidade, res.bairro, res.logradouro)
        console.log(this.address);
      })
    },
    openModal() {
      this.isModalOpen = !this.isModalOpen;
    },
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