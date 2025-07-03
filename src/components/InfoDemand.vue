<template>
    <div>
        <BForm v-if="demand">
            <BFormGroup label="Titulo">
                <BFormInput class="form-inputs" v-model="demand.title" disabled/>
            </BFormGroup>
            <BFormGroup label="Descrição">
                <BFormInput class="form-inputs" v-model="demand.description" disabled/>
            </BFormGroup>
            <BFormGroup label="Observação">
                <BFormInput class="form-inputs" v-model="demand.observation" disabled/>
            </BFormGroup>
            <BFormGroup label="Tipo">
                <BFormInput class="form-inputs" v-model="demand.type" disabled/>
            </BFormGroup>
            <hr>
            <BFormGroup label="UF">
                <BFormInput class="form-inputs" v-model="demand.uf" disabled/>
            </BFormGroup>
            <BFormGroup label="Cidade">
                <BFormInput class="form-inputs" v-model="demand.city" disabled/>
            </BFormGroup>
            <BFormGroup label="Bairro">
                <BFormInput class="form-inputs" v-model="demand.district" disabled/>
            </BFormGroup>
            <BFormGroup label="Rua">
                <BFormInput class="form-inputs" v-model="demand.street" disabled/>
            </BFormGroup>
            <hr>
            <BButton variant="primary" @click="close">Fechar</BButton>
        </BForm>
    </div>
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
            this.demand = null;
            this.isOpen = false;
            this.$emit('updateModalKey', true);
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