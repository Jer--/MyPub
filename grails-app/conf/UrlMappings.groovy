/*******************************************************************************
 * Author : Group BBHC
 * License : AGPL v3
 ******************************************************************************/
class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
