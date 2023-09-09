<template>
  <div>
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />

    <section class="site-section">
      <div class="container">
        <div class="row">
          <signin />

          <signup />
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import Signin from "@/components/forms/Signin.vue";
import Signup from "@/components/forms/Signup.vue";
import TitleSection from "@/components/titles/TitleSection.vue";

@Component({
  components: {
    Signin,
    Signup,
    TitleSection,
  },
})
export default class LoginComponent extends Vue {
  private breadcrumbList = ["Login"];
  private componentCaption = "Sign Up/Login";
}
</script>
