<template>
    <BModal v-model="isModalOpen" title="Adicionar Demanda">
      <BButton v-if="!isRegisterNewDemand" variant="primary" @click="openForm">Adicionar Demanda</BButton>
      <BForm v-if="isRegisterNewDemand">
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
        <BButton variant="primary" @click="save">Salvar</BButton>
      </BForm>
    </BModal>
</template>
<script>
import Address from '@/model/Address';
import Demand from '@/model/Demand';
import { DemandType } from '@/enums/DemandType';
export default {
    data() {
        return {
            isModalOpen: false,
            isRegisterNewDemand: false,
            typeDemands: [...Object.values(DemandType)],
            demand: new Demand(),
            address: new Address(),
        }
    },
    methods: {
        openModal(address) {
          this.address = address;
          this.isModalOpen = !this.isModalOpen;
        },
        openForm() {
          this.isRegisterNewDemand = !this.isRegisterNewDemand;
        },
        save() {
          this.$emit('updateDemands', this.demand);
          this.isModalOpen = !this.isModalOpen;
        }
    }
}
</script>