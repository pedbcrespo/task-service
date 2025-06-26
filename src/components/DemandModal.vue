<template>
    <BModal v-model="isModalOpen" title="Adicionar Demanda" no-footer>
      <BButton v-if="!isRegisterNewDemand" variant="primary" @click="openForm">Adicionar Demanda</BButton>
      <BForm v-if="isRegisterNewDemand">
        <BFormGroup>
          <BFormInput
          class="form-inputs"
          v-model="demand.title"
          placeholder="Nome da demanda"
          required
          />
          <BFormInput
          class="form-inputs"
          v-model="demand.description"
          placeholder="Descrição"
          required
          />
          <BFormInput
          class="form-inputs"
          v-model="demand.observation"
          placeholder="Observação"
          required
          />
          <BFormSelect
          class="form-inputs"
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
    props: ['demands'],
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
          this.isModalOpen = true;
          this.isRegisterNewDemand = false;
        },
        openForm() {
          this.isRegisterNewDemand = !this.isRegisterNewDemand;
        },
        save() {
          this.demand.setAddress(this.address);
          const newDemands = [...this.demands, this.demand];
          this.$emit('updateDemands', newDemands);
          this.isModalOpen = false;
          this.isRegisterNewDemand = false;
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