import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.9-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
	jacoco
}

group = "com.giovana.simoes"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

val kluentVersion = "1.68"
val mockkVersion = "2.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("com.ninja-squad:springmockk:$mockkVersion")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
	dependsOn(tasks.test) // tests are required to run before generating the report
}

jacoco {
	toolVersion = "0.8.7"
	reportsDirectory.set(layout.buildDirectory.dir("/home/giovanasimoes/Creditas/Projetos/Gbank_Project/Gbank/build/reports"))
}

tasks.jacocoTestReport {
	reports {
		xml.required.set(false)
		csv.required.set(false)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			limit {
				minimum = "0.5".toBigDecimal()
			}
		}

		rule {
			isEnabled = false
			element = "CLASS"
			includes = listOf("org.gradle.*")

			limit {
				counter = "LINE"
				value = "TOTALCOUNT"
				maximum = "0.3".toBigDecimal()
			}
		}
	}
}

//tasks.test {
//	configure<JacocoTaskExtension> {
//		isEnabled = true
//		destinationFile = layout.buildDirectory.file("jacoco/${name}.exec").get().asFile
//		includes = emptyList()
//		excludes = emptyList()
//		excludeClassLoaders = emptyList()
//		isIncludeNoLocationClasses = false
//		sessionId = "<auto-generated value>"
//		isDumpOnExit = true
//		classDumpDir = null
//		output = JacocoTaskExtension.Output.FILE
//		address = "localhost"
//		port = 6300
//		isJmx = false
//	}
//}

jacoco {
	toolVersion = "0.7.1.201405082137"
	reportsDir = file("$buildDir/customJacocoReportDir")
}
