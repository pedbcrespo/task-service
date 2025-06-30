<template>
  <BModal v-model="isModalOpen" title="Adicionar Demanda" no-footer>
      <BForm v-if="demand">
        <BFormGroup>
          <BFormInput
          class="form-inputs"
          v-model="demand.title"
          placeholder="Nome da demanda"
          required
          :disabled="disabled"
          />
          <BFormInput
          class="form-inputs"
          v-model="demand.description"
          placeholder="Descrição"
          required
          :disabled="disabled"
          />
          <BFormInput
          class="form-inputs"
          v-model="demand.observation"
          placeholder="Observação"
          :disabled="disabled"
          />
          <BFormSelect
          class="form-inputs"
          v-model="demand.type"
          placeholder="Selecione a classeficação da demanda"
          :options="arrDemandTypes"
          required
          :disabled="disabled"
          />
        </BFormGroup>
        <BButton v-show="!disabled" variant="primary" @click="save">Salvar</BButton>
      </BForm>
  </BModal>
</template>

<script lang="ts">
import { arrDemandTypes } from '@/enums/DemandType';
import Address from '@/model/Address';
import Demand from '@/model/Demand';

export default {
  data() {
    return {
      isModalOpen: false,
      disabled: false,
      demand: null,
      arrDemandTypes,
    };
  },
  methods: {
    openModal(address: Address) {
      if(!address) {
        this.isModalOpen = false;
        return;
      };
      console.log('MODAL OPEN')
      this.demand = new Demand(address);
      this.isModalOpen = true;
    },
    save() {
      this.$emit('updateDemands', this.demand);
      this.isModalOpen = false;
    },
  },
};
</script>
