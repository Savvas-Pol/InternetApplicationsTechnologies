<template>
  <section class="site-section block__18514" id="next-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-3 mr-auto">
          <div class="border p-4 rounded">
            <ul class="list-unstyled block__47528 mb-0">
              <li><span class="active">Name 1</span></li>
              <li><span class="active">Name 2</span></li>
              <li><span class="active">Name 3</span></li>
            </ul>
          </div>
        </div>
        <div class="col-lg-8">
          <h2 class="mb-4">Talking with ...</h2>

          <div class="block__87154 col-lg-12">
            <div class="block__91147 d-flex align-items-center">
              <figure class="mr-4">
                <img src="images/person_1.jpg" alt="Image" class="img-fluid" />
              </figure>
              <div>
                <h3>Elisabeth Smith</h3>
                <span
                  >Creaasdasdsdlkafj alk;dfj a;lsdjf ;lajsd fkalsd jftive
                  Director</span
                >
              </div>
            </div>
          </div>

          <div class="block__87154 col-lg-12">
            <div class="block__91147 d-flex align-items-center">
              <figure class="mr-4">
                <img src="images/person_1.jpg" alt="Image" class="img-fluid" />
              </figure>
              <div>
                <h3>You:</h3>
                <span
                  >Creaasdasdsdlkafj alk;dfj a;lsdjf ;lajsd fkalsd jftive
                  Director</span
                >
              </div>
            </div>
          </div>

          <input type="text" />
          <p>
            <a href="#" class="btn btn-primary btn-md mt-4">Send message</a>
          </p>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
@Component
export default class ConversationSection extends Vue {
  @Prop() private conversation!: Record<string, unknown>;
}
</script>
