package com.kotlin.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.kotlin.navigation.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    lateinit var binding: FragmentGameWonBinding
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)

        val args = GameWonFragmentArgs.fromBundle(this.arguments!!)
        Toast.makeText(context, "Num correct: ${args.numCorrectAnswers}, " +
                "Num questions: ${args.numQuestions}", Toast.LENGTH_LONG).show()

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()

        binding.nextMatchButton.setOnClickListener {
            navController.navigate(GameWonFragmentDirections.actionGameWonToGame())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.winner_menu, menu)
        if (getShareIntent().resolveActivity(activity!!.packageManager) == null) {
            menu.findItem(R.id.share).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numCorrectAnswers, args.numQuestions))

        return shareIntent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
}
