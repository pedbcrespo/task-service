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
          <l-circle-marker v-for="(demand, i) in demands" :key="i+1" :radius="3" color="blue" :lat-lng="[demand.location.lat, demand.location.lng]" @click="()=>openInfoModal(demand)"/>
          <l-circle-marker :lat-lng="[lat, lng]" :radius="2" color="red" />
        </l-map>

        <DemandModal @updateDemands="updateDemands" :isAllowOpen="isAllowOpenDemandModal" ref="demandModal"/>
        <InfoDemand ref="infoModal"/>
 
    </div>
</template>

<script>
import { LMap, LTileLayer, LMarker, LCircle, LCircleMarker } from '@vue-leaflet/vue-leaflet'
import 'leaflet/dist/leaflet.css'
import L from 'leaflet'
import Location from '@/model/Location'
import Address from '@/model/Address'
import AddressService from '@/service/AddressService'
import DemandModal from './DemandModal.vue'
import InfoDemand from './InfoDemand.vue'

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
    LCircleMarker,
    DemandModal,
    InfoDemand,
  },
  data() {
    return {
      lat: -22.9,
      lng: -43.2,
      zoom: 18,
      position: null,
      demands: [],
      address: new Address(),
      addressService: new AddressService(),
      isAllowOpenDemandModal: true,
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
      let currentLocation = new Location(e.latlng.lat, e.latlng.lng);
      this.addressService.getAddress(currentLocation.lat, currentLocation.lng).then(res => {
        this.address = new Address(res.uf, res.localidade, res.bairro, res.logradouro, currentLocation);
      }).then(() => {
        if(!this.isShowInfoModal)
          this.$refs.demandModal.openModal(this.address)
      });
    },
    updateDemands(demand) {
      this.demands = [...this.demands, demand];
      console.log(this.demands);
    },
    openInfoModal(demand) {
      this.$refs.infoModal.openModal(demand);
      this.isAllowOpenDemandModal = false;
    }
  },
  watch: {
    position(newPosition) {
      this.lat = newPosition.coords.latitude
      this.lng = newPosition.coords.longitude
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