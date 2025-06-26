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
import Demand from '@/model/Demand';
import { DemandType } from '@/enums/DemandType';
export default {
    props: [],
    data() {
        return {
            isModalOpen: false,
            isRegisterNewDemand: false,
            typeDemands: [...Object.values(DemandType)],
            demand: null,
            ischangable: false,
        }
    },
    methods: {
        openModal(isNewDemand=true, ischangable=false) {
          this.isModalOpen = true;
          this.isRegisterNewDemand = !isNewDemand;
          this.ischangable = ischangable;
        },
        openModalByAddress(address) {
          if(!address) return;
          this.demand = new Demand();
          this.demand.setAddress(address);
          this.openModal();
        },
        openModalByDemand(demand) {
          if(!demand) return;
          this.demand = demand;
          this.openModal();
        },
        openForm() {
          this.isRegisterNewDemand = !this.isRegisterNewDemand;
        },
        save() {
          this.$emit('updateDemands', this.demand);
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