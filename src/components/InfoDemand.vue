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
        <hr>
        <BButton variant="primary" @click="close">Fechar</BButton>
    </BForm>
</template>
<script lang="ts">
import Demand from '@/model/Demand';

export default {
    data() {
        return {
            isOpen: false,
            demand: null,
        }
    },
    methods: {
        open(demand:Demand) {
            if(!demand) return;
            this.demand = demand;
            this.isOpen = true;
        },
        close() {
            this.isOpen = false;
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