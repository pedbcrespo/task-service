<template>
    <div class="home">
        <map-location/>
    </div>
</template>
<script>
import MapLocation from './MapLocation.vue';
export default {
    components: {MapLocation},
    data() {
        return {}
    }
}
</script>
<style>
.home {
    width: 1400px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
</style>