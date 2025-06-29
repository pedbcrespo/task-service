<template>
    <BForm v-if="demand">
        <BFormInput disabled/>
        <BFormInput disabled/>
        <BFormInput disabled/>
    </BForm>
</template>
<script lang="ts">
import Demand from '@/model/Demand';

export default {
    data() {
        return {
            isModalOpen: false,
            demand: null,
        }
    },
    methods: {
        openModal(demand:Demand) {
            if(!demand) return;
            this.demand = demand;
            this.isModalOpen = true;
        }
    }
}
</script>