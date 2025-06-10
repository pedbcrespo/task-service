<template>
    <div class="map-location">
        <l-map
          style="height: 400px;"
          :zoom="zoom"
          :center="[lat, lng]"
          @click="onMapClick"
          @ready="onMapReady"
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

        <b-modal v-model="modal" title="Adicionar Demanda">  
          <b-form>
            <b-form-group>
              <b-form-input
                id="input-1"
                v-model="demand.title"
                type="email"
                placeholder="Enter email"
                required
              />
              <b-form-select
                id="input-3"
                v-model="demand.type"
                :options="typeDemands"
                required
              />
            </b-form-group>
          </b-form>
        </b-modal>
    </div>
</template>

<script>
import { LMap, LTileLayer, LMarker, LCircle, LCircleMarker } from '@vue-leaflet/vue-leaflet'
import 'leaflet/dist/leaflet.css'
import L from 'leaflet'
import Location from '@/model/Location'
import Demand from '@/model/Demand'

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
    },
    onMapReady(e) {
      console.log(e);
    },
    openModal() {
      this.isModalOpen = !this.isModalOpen;
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