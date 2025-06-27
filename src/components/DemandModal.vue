<template>
  <BModal v-model="isModalOpen" title="Adicionar Demanda" no-footer>
      <BForm>
        <BFormGroup>
          <BFormInput
          class="form-inputs"
          v-model="demand.title"
          placeholder="Nome da demanda"
          required
          :disabled="isShowDemandInfo"
          />
          <BFormInput
          class="form-inputs"
          v-model="demand.description"
          placeholder="Descrição"
          required
          :disabled="isShowDemandInfo"
          />
          <BFormInput
          class="form-inputs"
          v-model="demand.observation"
          placeholder="Observação"
          :disabled="isShowDemandInfo"
          />
          <BFormSelect
          class="form-inputs"
          v-model="demand.type"
          placeholder="Selecione a classeficação da demanda"
          :options="arrDemandTypes"
          required
          :disabled="isShowDemandInfo"
          />
        </BFormGroup>
        <BButton variant="primary" @click="save">Salvar</BButton>
      </BForm>
  </BModal>
</template>

<script>
import { arrDemandTypes } from '@/enums/DemandType';
import Demand from '@/model/Demand';

export default {
  data() {
    return {
      isModalOpen: false,
      demand: new Demand(),
      arrDemandTypes,
    };
  },
  computed: {
    isShowDemandInfo() {
      return !(this.demand && this.demand.title && this.demand.getAddress().location);
    },
  },
  methods: {
    openModal(address) {
      if(!address) return;
      this.demand.setAddress(address);
      this.isModalOpen = true;
    },
    save() {
      this.$emit('updateDemands', this.demand);
      this.isModalOpen = false;
    },
  },
};
</script>
