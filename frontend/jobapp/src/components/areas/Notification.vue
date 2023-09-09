<template>
  <section class="site-section" id="next-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 mb-4">
          <div class="block__87154">
            <blockquote>
              <p>
                &ldquo;Ipsum harum assumenda in eum vel eveniet numquam cumque
                vero vitae enim cupiditate deserunt eligendi officia modi
                consectetur. Expedita tempora quos nobis earum hic ex asperiores
                quisquam optio nostrum sit&rdquo;
              </p>
            </blockquote>
            <div class="block__91147 d-flex align-items-center">
              <figure class="mr-4">
                <img src="images/person_1.jpg" alt="Image" class="img-fluid" />
              </figure>
              <div>
                <h3>Elisabeth Smith</h3>
                <span class="position">Creative Director</span><br />
                <span class="mr-2">Accept</span>
                <span class="mr-2">Reject</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
@Component
export default class NotificationSection extends Vue {
  @Prop() private notification!: Record<string, unknown>;
}
</script>
