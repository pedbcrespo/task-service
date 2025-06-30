<template>
    <BForm v-if="demand">
        <BFormInput class="form-inputs" v-model="demand.title" disabled/>
        <BFormInput class="form-inputs" v-model="demand.description" disabled/>
        <BFormInput class="form-inputs" v-model="demand.observation" disabled/>
        <BFormInput class="form-inputs" v-model="demand.type" disabled/>
        <hr>
        <BFormInput class="form-inputs" v-model="demand.uf" disabled/>
        <BFormInput class="form-inputs" v-model="demand.city" disabled/>
        <BFormInput class="form-inputs" v-model="demand.district" disabled/>
        <BFormInput class="form-inputs" v-model="demand.street" disabled/>
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
<style>
.form-inputs {
    margin-top: 5px;
    margin-bottom: 5px;
}
</style>