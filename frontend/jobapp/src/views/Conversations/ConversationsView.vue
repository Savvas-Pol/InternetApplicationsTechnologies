<template>
  <div>
    <title-section
      :caption="componentCaption"
      :breadcrumbList="breadcrumbList"
    />
    <conversation-section />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import TitleSection from "@/components/titles/TitleSection.vue";
import ConversationSection from "@/components/areas/Conversation.vue";
// import articleService from "@/service/ArticleService";
// import authenticationService from "@/service/AuthenticationService";

@Component({
  components: {
    TitleSection,
    ConversationSection,
  },
})
export default class ConversationsView extends Vue {
  private breadcrumbList = ["Conversations"];
  private componentCaption = "Conversations";
  private pageSize = 3;

  private currentPage1 = 1;
  private pages1 = 1;
  public articles1 = [];

  private get getCurrentPage1() {
    if (this.articles1.length === 0) {
      return this.articles1;
    }
    return this.articles1.slice(
      (this.currentPage1 - 1) * this.pageSize,
      (this.currentPage1 - 1) * this.pageSize + this.pageSize
    );
  }

  private changePage1(i: number) {
    this.currentPage1 = i;
    console.log("page#1 changed to " + i);
  }

  mounted(): void {
    // console.log("ArticlesView mounted");
    // let myID = authenticationService.getUserID();
    // articleService
    //   .getMyArticles(myID)
    //   .then((response) => {
    //     this.articles1 = response.data;
    //     this.currentPage1 = 1;
    //     this.pages1 = Math.ceil(
    //       Math.max(this.articles1.length / this.pageSize, 1)
    //     );
    //     console.log(this.articles1);
    //     articleService
    //       .getFriendsArticles(myID)
    //       .then((response) => {
    //         this.articles2 = response.data;
    //         this.currentPage2 = 1;
    //         this.pages2 = Math.ceil(
    //           Math.max(this.articles2.length / this.pageSize, 1)
    //         );
    //         console.log(this.articles2);
    //         articleService
    //           .getFriendsLikedArticles(myID)
    //           .then((response) => {
    //             this.articles3 = response.data;
    //             this.currentPage3 = 1;
    //             this.pages3 = Math.ceil(
    //               Math.max(this.articles3.length / this.pageSize, 1)
    //             );
    //             console.log(this.articles3);
    //           })
    //           .catch((ex) => {
    //             console.log(ex);
    //             alert("Network error");
    //           });
    //       })
    //       .catch((ex) => {
    //         console.log(ex);
    //         alert("Network error");
    //       });
    //   })
    //   .catch((ex) => {
    //     console.log(ex);
    //     alert("Network error");
    //   });
  }
}
</script>
