<template>
	<div class="navigation">
		<ProfilNavigation />
		<v-container class="col-lg-4 mt-8">
			<v-card>
				<h2 class="pt-5">Change password</h2>
				<v-form ref="form" v-model="validForm" class="pa-5">
					<v-text-field
						placeholder="password actuel"
						v-model="password"
						:rules="passwordRule"
					></v-text-field>
					<v-text-field
						placeholder="new password"
						type="password"
						v-model="newPassword"
						:rules="newPasswordRule"
					></v-text-field>

					<v-card-actions class="justify-end"
						><v-btn class="teal white--text" @click="save"
							>Save</v-btn
						></v-card-actions
					>
				</v-form>
			</v-card>
		</v-container>
	</div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
export default {
	name: 'MotDePasse',
	components: { ProfilNavigation },
	data() {
		return {
			validForm: true,
			password: '',
			newPassword: '',
			passwordRule: [],
			newPasswordRule: []
		};
	},
	methods: {
		save() {}
	}
};
</script>

<style lang="scss" scoped>
</style>