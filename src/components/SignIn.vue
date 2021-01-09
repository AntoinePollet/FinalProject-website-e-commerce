<template>
	<v-container class="col-lg-4 mt-8">
		<v-card>
			<h2 class="pt-5">Sign in</h2>
			<v-form ref="form" v-model="validForm" class="pa-5">
				<v-text-field
					placeholder="email"
					v-model="email"
					:rules="emailRule"
				></v-text-field>
				<v-text-field
					placeholder="mot de passe"
					type="password"
					:rules="passwordRule"
				></v-text-field>
				<v-card-actions class="justify-end"
					><v-btn class="teal white--text" @click="signIn"
						>Sign in</v-btn
					></v-card-actions
				>
			</v-form>
		</v-card>
	</v-container>
</template>

<script>
export default {
	name: 'SignIn',
	data() {
		return {
			validForm: true,
			email: '',
			goTo: '/',
			emailRule: [v => !!v || 'Email requis !'],
			passwordRule: [v => !!v || 'Password requis !']
		};
	},
	beforeRouteEnter(to, from, next) {
		if (from.name === 'livraison') {
			next(vm => {
				vm.goTo = '/commande/livraison';
			});
		}
		next();
	},
	methods: {
		signIn() {
			if (this.$refs.form.validate()) {
				this.$store.dispatch('user/logIn');
				this.$router.push(this.goTo);
			}
		}
	}
};
</script>

<style lang="scss" scoped>
</style>