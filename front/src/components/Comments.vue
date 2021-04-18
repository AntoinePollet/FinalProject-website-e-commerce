<template>
	<div>
		<h1 class="pb-5">Commentaires ({{ comments.length }})</h1>
		<div v-if="comments.length != 0">
			<div
				v-for="item in comments"
				:key="item.id"
				class="comment d-flex justify-content-between py-7 px-4 mb-5"
			>
				<div style="border-right: thin solid black; padding-right: 10px">
					<div>{{ item.username }}</div>
					<div>{{ item.rating }}</div>
				</div>
				<div class="text-left pl-5" :style="{ flexGrow: 1 }">
					{{ item.message }}
				</div>
			</div>
		</div>
		<div v-else>Aucun Commentaire</div>
		<div v-if="ordered && loggedIn">
			<div v-if="!commented">
				<div class="col-2">
					<v-rating
						hover
						v-model="rating"
						length="5"
						half-increments
					></v-rating>
				</div>
				<v-textarea
					outlined
					v-model="description"
					placeholder="Saisir votre commentaire"
				>
				</v-textarea>
				<div class="d-flex justify-end">
					<v-btn
						class="white--text pink lighten-2"
						@click="submit"
						:disabled="!loggedIn"
						>Envoyer</v-btn
					>
				</div>
			</div>
			<div v-else>Vous avez déjà commenté</div>
		</div>
		<div v-else-if="loggedIn">
			Vous devez avoir acheté cet article pour pouvoir commenter !
		</div>
	</div>
</template>

<script>
import { mapState, mapGetters } from 'vuex';
export default {
	name: 'Comments',
	props: {
		idArticle: {
			type: String
		}
	},
	data() {
		return {
			description: '',
			rating: 0
		};
	},
	computed: {
		...mapState({
			loggedIn: state => state.user.isAuth,
			comments: state => state.user.commentArticle
		}),
		...mapGetters({
			hasOrdered: 'user/hasOrdered',
			hasCommented: 'user/hasCommented'
		}),
		commented() {
			return this.hasCommented;
		},
		ordered() {
			return this.hasOrdered(this.$route.params.id);
		}
	},
	methods: {
		async submit() {
			try {
				let body = {
					idArticle: this.idArticle,
					message: this.description,
					username: this.$store.state.user.username,
					rating: this.rating
				};
				await this.$store.dispatch('user/postComment', body);
			} catch (error) {}
		}
	}
};
</script>

<style lang="scss" scoped>
.comment {
	border-radius: 10px;
	&:nth-child(odd) {
		background-color: #eceff1;
	}
	&:nth-child(even) {
		background-color: #fce4ec;
	}
}
</style>