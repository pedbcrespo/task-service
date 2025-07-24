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
          <DemandTypeSelect v-model="demand.type"/>
        </BFormGroup>
        <BButton v-show="!disabled" variant="primary" @click="save">Salvar</BButton>
      </BForm>
  </BModal>
</template>

<script lang="ts">
import Address from '@/model/Address';
import Demand from '@/model/Demand';
import DemandTypeSelect from './shared/DemandTypeSelect.vue';
import CityDataCenterService from '@/service/CityDataCenterService';

export default {
  props:{
    isAllowOpen: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      isModalOpen: false,
      disabled: false,
      demand: new Demand(null),
      service: new CityDataCenterService(),
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
      this.isModalOpen = true && this.isAllowOpen;
    },
    save() {
      this.service.saveDemand(this.demand);
      this.$emit('updateDemands', this.demand);
      this.isModalOpen = false;
    },
  },
};
</script>
