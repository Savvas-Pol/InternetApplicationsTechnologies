<template>
  <div class="container col-lg-12">
    <div class="row align-items-center block__69944">
      <div class="col-md-6" v-if="!displayRight">
        <div
          v-if="
            showHiddenFields || user.scope_id_photo.description === 'Public'
          "
        >
          <img :src="user.photo" alt="Image" class="img-fluid mb-4 rounded" />
        </div>
        <div v-else>
          <img
            src="https://i.stack.imgur.com/JshmW.jpg"
            alt="Image"
            class="img-fluid mb-4 rounded"
          />
        </div>
        <!-- <img :src="user.photo" alt="Image" class="img-fluid mb-4 rounded" /> -->
      </div>
      <div class="col-md-6" v-if="!displayRight">
        <router-link :to="{ name: 'UserDetails', params: { id: user.id } }">
          <div v-if="user.role">
            <h3>
              {{ user.firstname }} {{ user.lastname }}
              <span v-if="user.role.id === 1">&#129351;</span>
            </h3>
          </div>
        </router-link>
        <div class="row col-lg-12">
          <div
            class="col-lg-6"
            v-if="
              showHiddenFields || user.scope_id_email.description === 'Public'
            "
          >
            &#9993; {{ user.email }}
          </div>
          <div class="col-lg-6" v-else>&#9993; hidden</div>
          <div
            class="col-lg-6"
            v-if="
              showHiddenFields || user.scope_id_phone.description === 'Public'
            "
          >
            &#9993; {{ user.phone }}
          </div>
          <div class="col-lg-6" v-else>&#9993; hidden</div>
        </div>
        <br />
        <h4>Privacy settings</h4>
        <ul>
          <div v-if="user.scope_id_email">
            <li>E-mail: {{ user.scope_id_email.description }}</li>
          </div>
          <div v-if="user.scope_id_photo">
            <li>Photo: {{ user.scope_id_photo.description }}</li>
          </div>
          <div v-if="user.scope_id_phone">
            <li>Phone: {{ user.scope_id_phone.description }}</li>
          </div>
        </ul>
        <p>
          {{ user.description }}
        </p>
        <template v-if="details">
          <h4>Experience</h4>
          <ol>
            <li v-for="obj in user.experience" v-bind:key="obj.id">
              {{ obj.title }} : {{ obj.date_from }} - {{ obj.date_to }}
            </li>
          </ol>
          <h4>Education</h4>
          <ol>
            <li v-for="obj in user.education" v-bind:key="obj.id">
              {{ obj.title }}
            </li>
          </ol>
          <h4>Skills</h4>
          <ol>
            <li v-for="obj in user.skill" v-bind:key="obj.id">
              {{ obj.description }}
            </li>
          </ol>
          <div class="social mt-4">
            <a href="#"><span class="icon-facebook"></span></a>
            <a href="#"><span class="icon-twitter"></span></a>
            <a href="#"><span class="icon-instagram"></span></a>
            <a href="#"><span class="icon-linkedin"></span></a>
          </div>
        </template>
      </div>

      <div class="col-md-6 order-md-2 ml-md-auto" v-if="displayRight">
        <img :src="user.photo" alt="Image" class="img-fluid mb-4 rounded" />
      </div>
      <div class="col-md-6" v-if="displayRight">
        <router-link :to="{ name: 'UserDetails', params: { id: user.id } }">
          <div v-if="user.role">
            <h3>
              {{ user.firstname }} {{ user.lastname }}
              <span v-if="user.role.id === 1">&#129351;</span>
            </h3>
          </div>
        </router-link>
        <div class="row col-lg-12">
          <div class="col-lg-6">&#9993; {{ user.email }}</div>
          <div class="col-lg-6">&#9742; {{ user.phone }}</div>
        </div>
        <br />
        <h4>Privacy settings</h4>
        <ul>
          <div v-if="user.scope_id_email">
            <li>E-mail: {{ user.scope_id_email.description }}</li>
          </div>
          <div v-if="user.scope_id_photo">
            <li>Photo: {{ user.scope_id_photo.description }}</li>
          </div>
          <div v-if="user.scope_id_phone">
            <li>Phone: {{ user.scope_id_phone.description }}</li>
          </div>
        </ul>
        <p>
          {{ user.description }}
        </p>
        <template v-if="details">
          <h4>Experience</h4>
          <ol>
            <li v-for="obj in user.experience" v-bind:key="obj.id">
              {{ obj.title }}
            </li>
          </ol>
          <h4>Education</h4>
          <ol>
            <li v-for="obj in user.education" v-bind:key="obj.id">
              {{ obj.title }}
            </li>
          </ol>
          <h4>Skills</h4>
          <ol>
            <li v-for="obj in user.skill" v-bind:key="obj.id">
              {{ obj.description }}
            </li>
          </ol>
          <div class="social mt-4">
            <a href="#"><span class="icon-facebook"></span></a>
            <a href="#"><span class="icon-twitter"></span></a>
            <a href="#"><span class="icon-instagram"></span></a>
            <a href="#"><span class="icon-linkedin"></span></a>
          </div>
        </template>
      </div>
    </div>
    <hr />
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
@Component
export default class UserInfo extends Vue {
  @Prop() private user!: Record<string, unknown>;
  @Prop() private alignment!: string;
  @Prop() private details!: boolean;
  @Prop() private showHiddenFields!: boolean;

  private get displayRight(): boolean {
    return this.alignment === "right";
  }
}
</script>
