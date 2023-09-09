<template>
  <section class="site-section" id="next-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 blog-content">
          <h3 class="mb-4">
            <h1>{{ article.title }}</h1>
          </h3>
          <p class="lead">
            {{ article.content }}
          </p>
          <div>Authored at : {{ article.authored_at }}</div>

          <div class="pt-5">
            <h3 @click.prevent="like" class="mb-5" v-if="article.users_liked">
              &#128077; {{ article.users_liked.length }} Likes
            </h3>
            <h3 class="mb-5" v-if="article.comment">
              {{ article.comment.length }} Comments
            </h3>
            <ul class="comment-list">
              <li
                class="comment"
                v-for="c in article.comment"
                v-bind:key="c.id"
              >
                <div class="vcard bio">
                  <img :src="c.user_id.photo" alt="Image placeholder" />
                </div>
                <div class="comment-body">
                  <h3>
                    {{ c.user_id.firstname }}
                    {{ c.user_id.lastname }}
                  </h3>
                  <div class="meta">January 9, 2018 at 2:21pm</div>
                  <p>
                    {{ c.content }}
                  </p>
                  <p><a href="#" class="reply">Reply</a></p>
                </div>
              </li>
            </ul>

            <div class="comment-form-wrap pt-5">
              <h3 class="mb-5">Leave a comment</h3>
              <form action="#" class="">
                <div class="form-group">
                  <label for="message">Message</label>
                  <textarea
                    name=""
                    id="message"
                    cols="30"
                    rows="10"
                    class="form-control"
                    v-model="createCommentData.content"
                  ></textarea>
                </div>
                <div class="form-group">
                  <input
                    @click.prevent="submitComment"
                    type="submit"
                    value="Post Comment"
                    class="btn btn-primary btn-md"
                  />
                </div>
              </form>
            </div>
          </div>
        </div>
        <div class="col-lg-4 sidebar pl-lg-5">
          <!-- <div class="sidebar-box">
            <form action="#" class="search-form">
              <div class="form-group">
                <span class="icon fa fa-search"></span>
                <input
                  type="text"
                  class="form-control form-control-lg"
                  placeholder="Type a keyword and hit enter"
                />
              </div>
            </form>
          </div> -->
          <div class="sidebar-box" v-if="article.user_id">
            <img
              :src="article.user_id.photo"
              alt="Image placeholder"
              class="img-fluid mb-4 w-50 rounded-circle"
            />
            <h3 v-if="article.user_id">
              About {{ article.user_id.firstname }}
              {{ article.user_id.lastname }}
            </h3>
            <p v-if="article.user_id">
              {{ article.user_id.description }}
            </p>
            <p><a href="#" class="btn btn-primary btn-sm">Read More</a></p>
          </div>

          <!-- <div class="sidebar-box">
            <div class="categories">
              <h3>Categories</h3>
              <li>
                <a href="#">Creatives <span>(12)</span></a>
              </li>
              <li>
                <a href="#">News <span>(22)</span></a>
              </li>
              <li>
                <a href="#">Design <span>(37)</span></a>
              </li>
              <li>
                <a href="#">HTML <span>(42)</span></a>
              </li>
              <li>
                <a href="#">Web Development <span>(14)</span></a>
              </li>
            </div>
          </div> -->

          <!-- <div class="sidebar-box">
            <h3>Paragraph</h3>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus
              itaque, autem necessitatibus voluptate quod mollitia delectus aut,
              sunt placeat nam vero culpa sapiente consectetur similique,
              inventore eos fugit cupiditate numquam!
            </p>
          </div> -->
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import articleService from "@/service/ArticleService";
import authenticationService from "@/service/AuthenticationService";

@Component
export default class ArticleDetails extends Vue {
  @Prop() private article!: Record<string, unknown>;

  private createCommentData = {
    article_id: 0,
    user_id: authenticationService.getUserID(),
    content: "",
  };

  private createLikeData = {
    article_id: 0,
    user_id: authenticationService.getUserID(),
  };

  private submitComment() {
    this.createCommentData.user_id = authenticationService.getUserID();

    this.createCommentData.article_id = this.article.id as number;

    articleService
      .submitComment(this.createCommentData)
      .then((res) => {
        let data = res.data;

        // this.article.comment.push(this.createCommentData);
        console.log("insert done");

        (this.article.comment as Array<unknown>).push(data);
      })
      .catch(() => {
        alert("Submit failed. Please try again later");
      });
  }

  private like() {
    this.createLikeData.user_id = authenticationService.getUserID();

    this.createLikeData.article_id = this.article.id as number;

    articleService
      .like(this.createCommentData)
      .then((res) => {
        let data = res.data;

        // this.article.comment.push(this.createCommentData);
        console.log("insert done");

        (this.article.comment as Array<unknown>).push(data);
      })
      .catch(() => {
        alert("Submit failed. Please try again later");
      });
  }
}
</script>
