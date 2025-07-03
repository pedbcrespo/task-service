<template>
  <BFormSelect
    class="form-inputs"
    v-model="localType"
    :options="arrDemandTypes"
    :disabled="disabled"
    required
    placeholder="Selecione a classificação da demanda"
    @change="updateDemandType"
  />
</template>

<script>
import { arrDemandTypes } from '@/enums/DemandType';
export default {
  props: {
    modelValue: { // aqui tratamos 'value' como sendo o objeto 'demand'
      type: String,
      required: true
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
        arrDemandTypes,
        localType: this.modelValue || null
    }
  },
  methods: {
    updateDemandType() {
      this.$emit('update:modelValue', this.localType);
    }
  },
}
</script>
