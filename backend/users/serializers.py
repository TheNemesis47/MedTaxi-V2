from rest_framework import serializers
from .models import CustomUser, NormalUser, Company


class RegisterSerializer(serializers.ModelSerializer):
    fiscal_code = serializers.CharField(required=False)
    birth_date = serializers.DateField(required=False)
    address = serializers.CharField(required=False)
    province = serializers.CharField(required=False)
    city = serializers.CharField(required=False)
    phone_number = serializers.CharField(required=False)
    company_name = serializers.CharField(required=False)
    vat_number = serializers.CharField(required=False)
    contact_person = serializers.CharField(required=False)

    class Meta:
        model = CustomUser
        fields = [
            'email', 'password', 'is_company',
            'fiscal_code', 'birth_date', 'address', 'province',
            'city', 'phone_number', 'company_name', 'vat_number', 'contact_person'
        ]
        extra_kwargs = {
            'password': {'write_only': True},
        }

    def create(self, validated_data):
        is_company = validated_data.pop('is_company', False)
        password = validated_data.pop('password')

        # Creazione dell'utente base
        user = CustomUser.objects.create(
            email=validated_data.get('email'),
            is_company=is_company
        )
        user.set_password(password)
        user.save()

        if is_company:
            # Creazione dell'azienda
            Company.objects.create(
                user=user,
                company_name=validated_data.get('company_name'),
                vat_number=validated_data.get('vat_number'),
                contact_person=validated_data.get('contact_person'),
            )
        else:
            # Creazione dell'utente normale
            NormalUser.objects.create(
                user=user,
                fiscal_code=validated_data.get('fiscal_code'),
                birth_date=validated_data.get('birth_date'),
                address=validated_data.get('address'),
                province=validated_data.get('province'),
                city=validated_data.get('city'),
            )

        return user
