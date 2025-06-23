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
      </BForm>
    </BModal>
</template>
<script>
export default {
    props:['demand'],
    data() {
        return {
            isModalOpen: false,
            isRegisterNewDemand: false,
            typeDemands: [],
        }
    },
    methods: {
        openModal() {
          this.isModalOpen = !this.isModalOpen;
        },
        openForm() {
          this.isRegisterNewDemand = !this.isRegisterNewDemand;
        }
    }
}
</script>