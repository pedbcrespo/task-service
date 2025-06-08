<template>
    <div class="map-location">
        <l-map
          style="height: 400px;"
          :zoom="16"
          :center="[lat, lng]"
          @click="onMapClick"
        >
          <l-tile-layer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <l-marker v-if="markerLat !== null" :lat-lng="[markerLat, markerLng]" />
        </l-map>
      
        <p v-if="markerLat !== null">
          Local clicado: {{ markerLat }}, {{ markerLng }}
        </p>
    </div>
</template>

<script>
import { LMap, LTileLayer, LMarker } from '@vue-leaflet/vue-leaflet'
import 'leaflet/dist/leaflet.css'
import L from 'leaflet'
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
    LMarker
  },
  data() {
    return {
      lat: -22.9,
      lng: -43.2,
      markerLat: null,
      markerLng: null,
      position: null,
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
      this.markerLat = e.latlng.lat
      this.markerLng = e.latlng.lng
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