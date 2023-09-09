<template>
  <div class="container">
    <div class="row align-items-center mb-5">
      <div class="col-lg-8 mb-4 mb-lg-0">
        <div class="d-flex align-items-center">
          <div>
            <h2>Create an Article</h2>
          </div>
        </div>
      </div>
      <div class="col-lg-4">
        <div class="row">
          <div class="col-6">
            <a
              href="#"
              @click.prevent="publish"
              class="btn btn-block btn-primary btn-md"
              >{{ buttonText }}</a
            >
          </div>
        </div>
      </div>
    </div>
    <div class="row mb-5">
      <div class="col-lg-12">
        <form
          @submit.prevent="publish"
          class="p-4 p-md-5 border rounded"
          method="post"
        >
          <h3 class="text-black mb-5 border-bottom pb-2">Article Details</h3>

          <div class="form-group">
            <label for="job-title">Article Title</label>
            <input
              type="text"
              class="form-control"
              id="job-title"
              placeholder="Type the article name"
              v-model="articleData.title"
            />
          </div>
          <div class="form-group">
            <label for="job-location">Content</label>

            <quill-editor ref="myQuillEditor" v-model="articleData.content" />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import authenticationService from "@/service/AuthenticationService";
import articleService from "@/service/ArticleService";

/* eslint-disable */
const { quillEditor } = require("vue-quill-editor");

import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";

@Component({
  components: {
    quillEditor,
  },
})
export default class ArticleCreate extends Vue {
  private articleData = {
    title: "",
    content: "",
    user_id: authenticationService.getUserID(),
  };
  private buttonText = "Publish";

  private showModal = false;

  private publish() {
    articleService
      .publish(this.articleData)
      .then(() => {
        this.buttonText = "Published";
      })
      .catch((response) => {
        alert("An error has occured");
      });
  }
}
</script>
