<template>
  <div class="col-lg-6">
    <h2 class="mb-4">Export data</h2>
    <form action="#" class="p-4 border rounded">
      <div class="row form-group">
        <div class="col-md-12 mb-3 mb-md-0">
          <input
            class="form-check-input"
            type="radio"
            name="flexRadioDefault"
            id="flexRadioDefault1"
            v-model="data_format"
            value="xml"
          />
          <label class="form-check-label" for="flexRadioDefault1">
            Export data as XML
          </label>
        </div>
      </div>
      <div class="row form-group mb-4">
        <div class="col-md-12 mb-3 mb-md-0">
          <input
            class="form-check-input"
            type="radio"
            name="flexRadioDefault"
            id="flexRadioDefault2"
            checked
            v-model="data_format"
            value="json"
          />
          <label class="form-check-label" for="flexRadioDefault2">
            Export data as JSON
          </label>
        </div>
      </div>

      <div class="col-md-12">
        <input
          type="submit"
          value="Download"
          class="btn px-4 btn-primary text-white"
          @click.prevent="download"
        />
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import administrationService from "@/service/AdministrationService";

@Component
export default class ExportData extends Vue {
  private data_format = "json";

  private download() {
    if (this.data_format == "xml") {
      administrationService
        .downloadXML()
        .then((response) => {
          const blob = new Blob([response.data], { type: "application/xml" });
          const link = document.createElement("a");
          link.href = URL.createObjectURL(blob);
          link.download = "data.xml";
          link.click();
          URL.revokeObjectURL(link.href);
        })
        .catch((response) => {
          alert("Error while downloading data ..");
        });
    } else {
      administrationService
        .downloadJSON()
        .then((response) => {
          const blob = new Blob([response.data], { type: "application/json" });
          const link = document.createElement("a");
          link.href = URL.createObjectURL(blob);
          link.download = "data.json";
          link.click();
          URL.revokeObjectURL(link.href);
        })
        .catch((response) => {
          alert("Error while downloading data ..");
        });
    }
  }
}
</script>
