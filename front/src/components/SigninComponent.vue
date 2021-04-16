<template>
	<div class="d-flex justify-center">
		Vous devez vous connecter pour continuer
		<v-dialog v-model="dialogSignin" width="500">
			<template v-slot:activator="{ on, attrs }">
				<div class="black--text mb-0 mr-2 ml-3" text v-on="on" v-bind="attrs">
					<p><span class="pa-3 signin">Sign in</span></p>
				</div>
			</template>
			<Signin @signup="signupDialog" />
		</v-dialog>

		<v-dialog v-model="dialogSignup" width="500">
			<Signup @signin="signinDialog" />
		</v-dialog>
	</div>
</template>

<script>
import Signin from './SignInModal.vue';
import Signup from './Signup.vue';

export default {
	name: 'SigninComponent',
	components: { Signin, Signup },
	data() {
		return {
			dialogSignin: false,
			dialogSignup: false
		};
	},
	methods: {
		signinDialog() {
			(this.dialogSignin = true), (this.dialogSignup = false);
		},
		signupDialog() {
			(this.dialogSignin = false), (this.dialogSignup = true);
		}
	}
};
</script>

<style lang="scss" scoped>
.signin {
	border-radius: 20px;
	border: 2px solid $primary !important;
	color: $primary;
	font-weight: bold;
}
.signin:hover {
	background-color: $primary;
	color: white;
}
</style>