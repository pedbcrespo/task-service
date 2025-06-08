<template>
    <div class="map-location">
        <l-map
          style="height: 400px;"
          :zoom="16"
          :center="[lat, lng]"
          @click="onMapClick"
          @ready="onMapReady"
          @update:center="onCenterUpdate"
        >
          <l-tile-layer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <l-marker v-if="markerLat !== null" :lat-lng="[markerLat, markerLng]" />
          <l-circle-marker :lat-lng="[lat, lng]" :radius="2" color="red" />
        </l-map>
      
        <p v-if="markerLat !== null">
          Local clicado: {{ markerLat }}, {{ markerLng }}
        </p>
    </div>
</template>

<script>
import { LMap, LTileLayer, LMarker, LCircle, LCircleMarker } from '@vue-leaflet/vue-leaflet'
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
    LMarker,
    LCircle,
    LCircleMarker
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
    },
    onMapReady(e) {
      console.log(e);

      // map.on('locationfound', (e) => {
      //   // Círculo azul da posição
      //   L.circle(e.latlng, {
      //     radius: e.accuracy,
      //     color: '#136AEC',
      //     fillColor: '#136AEC',
      //     fillOpacity: 0.2
      //   }).addTo(map))
    },
    onCenterUpdate(e) {
      console.log('CENTER UPDATED:', e);
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